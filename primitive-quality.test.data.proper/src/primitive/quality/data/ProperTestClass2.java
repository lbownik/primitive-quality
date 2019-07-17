package primitive.quality.data;

import org.junit.Test;
import primitive.quality.Quality;

/*******************************************************************************
 *@author lukasz.bownik@gmail.com
 ******************************************************************************/
public class ProperTestClass2 {

	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	@Quality(verificationRatio = 1, useCaseCoverage = 0.5, complexityImpact = 0.1, comment = "Comment3")
	public void properTest3() {

	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	@Quality(verificationRatio = 0.7, useCaseCoverage = 1.0, complexityImpact = 0.9, comment = "Comment4")
	public void properTest4() {

	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Quality(verificationRatio = 1, useCaseCoverage = 0.5, complexityImpact = 0.1, comment = "Comment1")
	public void improperTest3() {

	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void improperTest4() {

	}
}
