package br.com.cds.connecta.presenter.business.strategy.querybuilder;

import br.com.cds.connecta.presenter.entity.querybuilder.QueryCondition;
import br.com.cds.connecta.presenter.entity.querybuilder.QueryConditionSolr;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class InQueryPredicateStrategy implements QueryPredicateStrategy {

    @Override
    public String getPredicateFor(QueryCondition condition, List<Object> parameters) {
        List<String> inSQL = new ArrayList<>();
        for (String in : condition.getValue().getIn()) {
            inSQL.add("?");
            if (in.matches("[0-9]+")) {
                parameters.add(in);
            }else{
                parameters.add("'" + in + "'");
            }
        }

        String join = StringUtils.join(inSQL, ",");

        String negate = "";
        if (condition.getPredicate().isNegation()) {
            negate = " NOT ";
        }

        return " UPPER(attr" + condition.getAttribute().getId() + ") "
                + negate + " IN (" + join + ")";
    }

    @Override
    public String getPredicateForSolr(QueryConditionSolr condition, List<Object> parameters) {
       List<String> inSQL = new ArrayList<>();
         for (String in : condition.getValue().getIn()) {
            inSQL.add(in);
            if (in.matches("[0-9]+")) {
                parameters.add(in);
            }else{
                parameters.add("'" + in + "'");
            }
        }
         String join = StringUtils.join(inSQL, ",");
         
         
         String negate = "+";
        if (condition.getPredicate().isNegation()) {
            negate = "-";
        }
        
        return "(" + negate + condition.getName() + ":" + join + ")";
    }
}
