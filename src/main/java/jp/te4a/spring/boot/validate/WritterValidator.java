package jp.te4a.spring.boot.validate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
public class WritterValidator implements ConstraintValidator<Writter, String>{
	String param;
	@Override
	public void initialize(Writter nv){ param =  nv.param(); }
	@Override
	public boolean isValid(String in,ConstraintValidatorContext cxt){
		if(in == null){
			return false;
		}
		System.out.println(in.equals(param));
		return !in.equals(param);
	}
}
