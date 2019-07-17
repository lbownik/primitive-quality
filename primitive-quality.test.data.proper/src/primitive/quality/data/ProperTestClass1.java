package primitive.quality.data;

import org.junit.Test;
import primitive.quality.Quality;

/*******************************************************************************
 *@author lukasz.bownik@gmail.com
 ******************************************************************************/
public class ProperTestClass1 {

	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	@Quality(verificationRatio = 1, useCaseCoverage = 0.5, complexityImpact = 0.1, comment = "Comment1")
	public void properTest1() {

	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	@Quality(verificationRatio = 0.7, useCaseCoverage = 1.0, complexityImpact = 0.9, comment = "Comment2")
	public void properTest2() {

	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Quality(verificationRatio = 1, useCaseCoverage = 0.5, complexityImpact = 0.1, comment = "Comment1")
	public void improperTest1() {

	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void improperTest2() {

	}
}
