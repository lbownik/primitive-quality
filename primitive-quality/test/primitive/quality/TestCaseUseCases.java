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
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/*******************************************************************************
 *@author lukasz.bownik@gmail.com
 ******************************************************************************/
public class TestCaseUseCases {

	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void testCasesOf_returnEmptyList_forEmptyTestSuite() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.empty/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final List<TestCase> cases = TestCase.testCasesOf(path);

		assertTrue(cases.isEmpty());
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void testCasesOf_returnNonEmptyList_forNonEmptyTestSuite() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.proper/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final List<TestCase> cases = TestCase.testCasesOf(path);

		assertEquals(4, cases.size());

		TestCase tc = cases.stream().filter(t -> t.getMethod().toString().
				  endsWith("ProperTestClass1.properTest1()")).findFirst().get();
		assertEquals(1.0, tc.getVerificationRatio(), 0.001);
		assertEquals(0.5, tc.getUseCaseCoverage(), 0.001);
		assertEquals(0.1, tc.getComplexityImpact(), 0.001);
		assertEquals(0.05, tc.getCoveredComplexity(), 0.001);
		assertEquals("Comment1", tc.getComment());
		assertEquals("TestCase{method=public void primitive.quality.data.ProperTestClass1.properTest1() verificationRatio=1.0 useCaseCoverage=0.5 complexityImpact=0.1 comment=Comment1}", tc.toString());

		tc = cases.stream().filter(t -> t.getMethod().toString().
				  endsWith("ProperTestClass1.properTest2()")).findFirst().get();
		assertEquals(0.7, tc.getVerificationRatio(), 0.001);
		assertEquals(1.0, tc.getUseCaseCoverage(), 0.001);
		assertEquals(0.9, tc.getComplexityImpact(), 0.001);
		assertEquals(0.63, tc.getCoveredComplexity(), 0.001);
		assertEquals("Comment2", tc.getComment());
		assertEquals("TestCase{method=public void primitive.quality.data.ProperTestClass1.properTest2() verificationRatio=0.7 useCaseCoverage=1.0 complexityImpact=0.9 comment=Comment2}", tc.toString());

		tc = cases.stream().filter(t -> t.getMethod().toString().
				  endsWith("ProperTestClass2.properTest3()")).findFirst().get();
		assertEquals(1.0, tc.getVerificationRatio(), 0.001);
		assertEquals(0.5, tc.getUseCaseCoverage(), 0.001);
		assertEquals(0.1, tc.getComplexityImpact(), 0.001);
		assertEquals(0.05, tc.getCoveredComplexity(), 0.001);
		assertEquals("Comment3", tc.getComment());
		assertEquals("TestCase{method=public void primitive.quality.data.ProperTestClass2.properTest3() verificationRatio=1.0 useCaseCoverage=0.5 complexityImpact=0.1 comment=Comment3}", tc.toString());

		tc = cases.stream().filter(t -> t.getMethod().toString().
				  endsWith("ProperTestClass2.properTest4()")).findFirst().get();
		assertEquals(0.7, tc.getVerificationRatio(), 0.001);
		assertEquals(1.0, tc.getUseCaseCoverage(), 0.001);
		assertEquals(0.9, tc.getComplexityImpact(), 0.001);
		assertEquals(0.63, tc.getCoveredComplexity(), 0.001);
		assertEquals("Comment4", tc.getComment());
		assertEquals("TestCase{method=public void primitive.quality.data.ProperTestClass2.properTest4() verificationRatio=0.7 useCaseCoverage=1.0 complexityImpact=0.9 comment=Comment4}", tc.toString());
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void testCasesOf_returnNonEmptyList_forNonEmptyTestSuiteInJar() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.proper/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final List<TestCase> cases = TestCase.testCasesOf(path);

		assertEquals(4, cases.size());
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void testCasesOf_throwsNullPointer_forNullParameter() throws Exception {

		try {
			final List<TestCase> cases = TestCase.testCasesOf(null);
			fail();
		} catch (final NullPointerException e) {
			assertTrue(true);
		}
	}
		/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void testCasesOf_throwsNoSuchFileException_forInexistentPath() throws Exception {

		try {
		final Path path = Paths.get("../primitive-quality.test.data.proper/build/xyz").
				  toAbsolutePath().normalize();
		assertFalse(Files.exists(path));

		final List<TestCase> cases = TestCase.testCasesOf(path);
			fail();
		} catch (final NoSuchFileException e) {
			assertTrue(true);
		}
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void equals_returnsFalse_forNullParameter() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.proper/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final TestCase tc = TestCase.testCasesOf(path).get(0);

		assertFalse(tc.equals(null));
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void equals_returnsFalse_forIncompatibleClass() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.proper/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final TestCase tc = TestCase.testCasesOf(path).get(0);

		assertFalse(tc.equals("123"));
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Test
	public void equals_returnsTure_forTheSameObject() throws Exception {

		final Path path = Paths.get("../primitive-quality.test.data.proper/build/classes").
				  toAbsolutePath().normalize();
		assertTrue(Files.exists(path));

		final TestCase tc = TestCase.testCasesOf(path).get(0);

		assertTrue(tc.equals(tc));
	}
}
