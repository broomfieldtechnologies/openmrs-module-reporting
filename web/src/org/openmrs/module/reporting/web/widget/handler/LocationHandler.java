/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.reporting.web.widget.handler;

import org.openmrs.Location;
import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.web.widget.WidgetTag;
import org.openmrs.module.reporting.web.widget.html.CodedWidget;
import org.openmrs.module.reporting.web.widget.html.Option;

/**
 * FieldGenHandler for Enumerated Types
 */
@Handler(supports={Location.class}, order=50)
public class LocationHandler extends CodedHandler {
	
	/** 
	 * @see CodedHandler#populateOptions(WidgetTag, CodedWidget)
	 */
	@Override
	public void populateOptions(WidgetTag tag, CodedWidget widget) {
		for (Location l : Context.getLocationService().getAllLocations()) {
			widget.addOption(new Option(l.getUuid(), l.getName(), null, l));
		}
	}
}
