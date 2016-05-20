package org.openmrs.module.reporting.data.obs.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.data.DataUtil;
import org.openmrs.module.reporting.data.encounter.definition.ConvertedEncounterDataDefinition;
import org.openmrs.module.reporting.data.obs.EvaluatedObsData;
import org.openmrs.module.reporting.data.obs.definition.ConvertedObsDataDefinition;
import org.openmrs.module.reporting.data.obs.definition.ObsDataDefinition;
import org.openmrs.module.reporting.data.obs.service.ObsDataService;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;

/**
 * Evaluates a ConvertedObsDataDefinition
 */
@Handler(supports=ConvertedEncounterDataDefinition.class, order=50)
public class ConvertedObsDataEvaluator implements ObsDataEvaluator {

    public EvaluatedObsData evaluate(ObsDataDefinition definition, EvaluationContext context) throws EvaluationException {
        EvaluatedObsData c = new EvaluatedObsData(definition, context);
        ConvertedObsDataDefinition def = (ConvertedObsDataDefinition)definition;
        EvaluatedObsData unconvertedData = Context.getService(ObsDataService.class).evaluate(def.getDefinitionToConvert(), context);
        if (def.getConverters().isEmpty()) {
            c.setData(unconvertedData.getData());
        }
        else {
            for (Integer id : unconvertedData.getData().keySet()) {
                Object val = DataUtil.convertData(unconvertedData.getData().get(id), def.getConverters());
                c.addData(id, val);
            }
        }
        return c;
    }
}
