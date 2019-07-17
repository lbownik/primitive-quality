package primitive.quality;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*******************************************************************************
 * @author lukasz.bownik@gmail.com
*******************************************************************************/
@Retention(RetentionPolicy.RUNTIME)
public @interface Quality {
	
	public double verificationRatio();
	
	public double useCaseCoverage();
	
	public double complexityImpact();
	
	public String comment();
}
