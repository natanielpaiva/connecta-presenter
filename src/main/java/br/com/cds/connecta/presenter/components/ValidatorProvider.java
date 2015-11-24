package br.com.cds.connecta.presenter.components;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
@Component
public class ValidatorProvider {

    private final Validator validator;

    public ValidatorProvider() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    public Validator validator() {
        return validator;
    }

    /**
     * @param object
     * 
     * TODO Retornar violations?
     * @return 
     */
    public Set<ConstraintViolation<Object>> basicValidate(Object object) {
        return validator().validate(object);
    }
}
