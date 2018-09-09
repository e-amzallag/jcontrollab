/*
 * Copyright 2018 Erik Amzallag
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.dajlab.jcontrollab.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dajlab.core.CommandEvent;
import org.dajlab.core.CommandListener;
import org.dajlab.core.DaJLabModule;
import org.dajlab.gui.AbstractDajlabTab;
import org.dajlab.gui.extension.DajlabControllerExtensionInterface;
import org.dajlab.gui.extension.TabExtensionInterface;
import org.dajlab.jcontrollab.core.ControlLabInterface;
import org.dajlab.jcontrollab.core.ControlLabManager;
import org.dajlab.jcontrollab.core.OutputPortEnum;
import org.dajlab.jcontrollab.gui.model.ConsoleModel;
import org.dajlab.jcontrollab.gui.model.JControlLabModel;
import org.dajlab.jcontrollab.gui.model.OutputModel;
import org.dajlab.jcontrollab.gui.view.CommandControlLabEvent;
import org.dajlab.jcontrollab.gui.view.ControlLabTab;

/**
 * JControlLab controller for JControlLab application.
 * 
 * @author Erik Amzallag
 *
 */
public class JControlLabController
		implements DajlabControllerExtensionInterface<JControlLabModel>, TabExtensionInterface, CommandListener {

	/**
	 * Control lab interfaces map.
	 */
	private Map<String, ControlLabInterface> labControllers;

	/**
	 * Main model.
	 */
	private JControlLabModel mainModel = new JControlLabModel();

	/**
	 * Logger.
	 */
	private static Logger logger = LogManager.getLogger(JControlLabController.class);

	/**
	 * List of tabs.
	 */
	private List<AbstractDajlabTab> tabs;

	/**
	 * Modules maps. Each output is seen as a {@link DaJLabModule}.
	 */
	private Map<String, Map<OutputPortEnum, SimpleRandomModule>> modules;

	/**
	 * Prefix for localization.
	 */
	private static final String LABELS_PREFIX = "jcontrollab";

	/**
	 * Control lab Manager.
	 */
	private ControlLabManager manager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AbstractDajlabTab> getTabs() {

		if (tabs == null && CollectionUtils.isNotEmpty(mainModel.getControlLabs().values())) {
			tabs = new ArrayList<>(mainModel.getControlLabs().size());
			for (ConsoleModel consoleModel : mainModel.getControlLabs().values()) {
				ControlLabTab tab = new ControlLabTab(consoleModel, this);
				tabs.add(tab);
			}
		}
		return tabs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connect() {

		manager = new ControlLabManager();
		manager.connect();
		List<ControlLabInterface> controllabs = manager.getControlLabs();
		labControllers = new HashMap<String, ControlLabInterface>(controllabs.size());
		modules = new HashMap<String, Map<OutputPortEnum, SimpleRandomModule>>(1);
		int i = 0;
		for (ControlLabInterface controlLab : controllabs) {
			labControllers.put(controlLab.getPort(), controlLab);
			ConsoleModel model = new ConsoleModel(i, "ControlLab " + i, controlLab.getPort());
			mainModel.getControlLabs().put(Integer.toString(i), model);

			modules.put(Integer.toString(i), new TreeMap<OutputPortEnum, SimpleRandomModule>());
			Map<OutputPortEnum, SimpleRandomModule> controllerModules = modules.get(Integer.toString(i));
			// Create ouputs
			for (OutputPortEnum outputPort : model.getOutputModelsMap().keySet()) {
				// model
				OutputModel outputModel = model.getOutputModelsMap().get(outputPort);
				// module
				SimpleRandomModule module = new SimpleRandomModule(controlLab, outputModel);
				controllerModules.put(outputPort, module);
			}
			i++;
		}

		// For test purpose only when no physical control lab...
		// ConsoleModel model = new ConsoleModel(i, "ControlLab " + i, "COM1");
		// mainModel.getControlLabs().put(Integer.toString(i), model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disconnect() {

		// Stopping the modules
		for (Map<OutputPortEnum, SimpleRandomModule> mapModules : modules.values()) {
			for (SimpleRandomModule module : mapModules.values()) {
				module.stop();
			}
		}

		for (ControlLabInterface controller : labControllers.values()) {
			controller.disconnect();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateModel(JControlLabModel model) {

		if (model != null) {
			// Simple algo "all or nothing".
			// TODO improve the algo

			Set<String> connectedLabPorts = labControllers.keySet();
			Set<String> loadedLabPorts = new HashSet<>();
			for (ConsoleModel clm : model.getControlLabs().values()) {
				loadedLabPorts.add(clm.getPort());
			}
			if (CollectionUtils.isEqualCollection(connectedLabPorts, loadedLabPorts)) {
				for (String idClm : model.getControlLabs().keySet()) {
					ConsoleModel modelToUpdate = mainModel.getControlLabs().get(idClm);
					ConsoleModel newModel = model.getControlLabs().get(idClm);
					updateConsoleModel(modelToUpdate, newModel);
				}
			} else {
				// TODO raise alert.
			}
		}

	}

	/**
	 * Update the model.
	 * 
	 * @param modelToUpdate
	 *            current model to update
	 * @param newModel
	 *            model loaded
	 */
	private void updateConsoleModel(final ConsoleModel modelToUpdate, final ConsoleModel newModel) {

		modelToUpdate.setTitle(newModel.getTitle());
		for (OutputPortEnum port : OutputPortEnum.values()) {
			OutputModel ouputModel = modelToUpdate.getOutputModelsMap().get(port);
			OutputModel newOuputModel = newModel.getOutputModelsMap().get(port);
			ouputModel.setLabel(newOuputModel.getLabel());
			ouputModel.setOnOff(newOuputModel.isOnOff());
			ouputModel.setPower(newOuputModel.getPower());
			ouputModel.setReverse(newOuputModel.isReverse());
			ouputModel.setStartOn(newOuputModel.getStartOn());
			ouputModel.setStartOff(newOuputModel.getStartOff());
			ouputModel.setStopOn(newOuputModel.getStopOn());
			ouputModel.setStopOff(newOuputModel.getStopOff());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JControlLabModel getModel() {

		return mainModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newCommand(final CommandEvent ev) {

		if (ev instanceof CommandControlLabEvent) {
			CommandControlLabEvent event = (CommandControlLabEvent) ev;
			OutputModel outputModel = event.getModel();
			String consoleNumber = Integer.toString(outputModel.getConsoleNumber());
			SimpleRandomModule module = modules.get(consoleNumber).get(outputModel.getPort());

			switch (event.getCommand()) {
			case ONOFF:
				if (outputModel.isOnOff()) {
					module.startMotor();
				} else {
					module.stop();
				}
				break;
			case POWER:
				module.setPower(outputModel.getPower());
				break;
			case REV:
				module.reverse();
				break;
			case STARTON:
			case STOPON:
			case STARTOFF:
			case STOPOFF:
				module.updateRange();
				break;
			default:
				break;
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLocalization() {

		return LABELS_PREFIX;
	}

	/**
	 * @return the labControllers
	 */
	public final Map<String, ControlLabInterface> getLabControllers() {
		return labControllers;
	}

}
