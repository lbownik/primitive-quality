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

import static java.util.Collections.unmodifiableList;
import java.util.List;
import java.util.function.ToDoubleFunction;

/*******************************************************************************
 *@author lukasz.bownik@gmail.com
 ******************************************************************************/
final class Report {

	/****************************************************************************
	 *
	 ***************************************************************************/
	Report(final List<TestCase> cases) {

		this.cases = unmodifiableList(cases);
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	public List<TestCase> getCases() {

		return this.cases;
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	public double getAverageVerificationRatio() {

		return averageOf(TestCase::getVerificationRatio);
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	public double getAverageUseCaseCoverage() {

		return averageOf(TestCase::getUseCaseCoverage);
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	public double getAverageComplexityImpact() {

		return averageOf(TestCase::getComplexityImpact);
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	public double getAverageCoveredComplexity() {

		return averageOf(TestCase::getCoveredComplexity);
	}
   /****************************************************************************
	 *
	 ***************************************************************************/
	public long getNumberOfAnnotatedCases() {

		return this.cases.stream().filter(TestCase::isAnnotated).count();
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	private double averageOf(final ToDoubleFunction<TestCase> f) {

		return this.cases.stream().mapToDouble(f).average().orElse(0);
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	private final List<TestCase> cases;
}
