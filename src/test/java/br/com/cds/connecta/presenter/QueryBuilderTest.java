package br.com.cds.connecta.presenter;

import br.com.cds.connecta.presenter.business.builder.IQueryBuilder;
import org.junit.Test;
import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
import br.com.cds.connecta.presenter.domain.QueryPredicateEnum;
import br.com.cds.connecta.presenter.entity.Attribute;
import br.com.cds.connecta.presenter.entity.Query;
import br.com.cds.connecta.presenter.entity.QueryCondition;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 *
 * @author pires
 */
@TransactionConfiguration
public class QueryBuilderTest extends BaseTest {
    
    @Autowired
    private IQueryBuilder builder;
    
    @Test
    public void testMappingPersists() {
        Attribute attribute = new Attribute();
        attribute.setId(1L);
        
        QueryCondition condition = new QueryCondition();
        
        condition.setAttribute(attribute);
        condition.setPredicate(QueryPredicateEnum.EQUAL);
        condition.setOperator(QueryOperatorEnum.AND);
        condition.setValue("TEST");
        
        List<QueryCondition> conditions = new ArrayList<>();
        
        conditions.add(condition);
        
        Query query = new Query();
        query.setConditions(conditions);
        
        query = builder.save(query);
        
        assertThat(query.getId(), allOf(notNullValue(), greaterThan(0L)));
        assertThat(query.getConditions(), hasSize(greaterThan(0)));
    }
}
