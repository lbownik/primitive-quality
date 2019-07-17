//BSD 3-Clause License
//
//Copyright (c) 2017, Łukasz Bownik
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without
//modification, are permitted provided that the following conditions are met:
//
//* Redistributions of source code must retain the above copyright notice, this
//  list of conditions and the following disclaimer.
//
//* Redistributions in binary form must reproduce the above copyright notice,
//  this list of conditions and the following disclaimer in the documentation
//  and/or other materials provided with the distribution.
//
//* Neither the name of the copyright holder nor the names of its
//  contributors may be used to endorse or promote products derived from
//  this software without specific prior written permission.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
//AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
//IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
//FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
//DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
//CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
//OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
//OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package primitive.quality;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*******************************************************************************
 *@author lukasz.bownik@gmail.com
 ******************************************************************************/
public class ReportUseCases {

	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void methods_returnProperValues_forNonEmptyTestSuite() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.proper/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final Report report = new Report(TestCase.testCasesOf(path));

		assertEquals((1.0+0.7+1.0+0.7)/4, report.getAverageVerificationRatio(), 0.001);
		assertEquals((0.5+1.0+0.5+1.0)/4, report.getAverageUseCaseCoverage(), 0.001);
		assertEquals((0.1+0.9+0.1+0.9)/4, report.getAverageComplexityImpact(), 0.001);
		assertEquals((0.05+0.63+0.05+0.63)/4, report.getAverageCoveredComplexity(), 0.001);
	}
		/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void methods_returnZeros_forEmptyTestSuite() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.proper/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final Report report = new Report(new ArrayList<>());

		assertEquals(0, report.getAverageVerificationRatio(), 0.001);
		assertEquals(0, report.getAverageUseCaseCoverage(), 0.001);
		assertEquals(0, report.getAverageComplexityImpact(), 0.001);
		assertEquals(0, report.getAverageCoveredComplexity(), 0.001);
	}
}