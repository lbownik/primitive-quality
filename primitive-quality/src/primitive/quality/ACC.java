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

import java.io.Writer;
import static java.nio.file.Files.*;
import java.nio.file.Paths;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/*******************************************************************************
 *@author lukasz.bownik@gmail.com
 *******************************************************************************/
public final class ACC extends Task {
	/****************************************************************************
	 *
	 ***************************************************************************/
	@Override
	public void execute() throws BuildException {

		try {
			final Report r = new Report(TestCase.testCasesOf(Paths.get(this.testDir)));
			printReport(r);
		} catch (final Exception e) {
			throw new BuildException(e);
		}
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	public void setTestDir(final String testDir) {

		this.testDir = testDir;
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	public void setOutputFile(final String outputFile) {

		this.outputFile = outputFile;
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	private void printReport(final Report report)
			  throws Exception {

		if (this.outputFile != null) {
			try (final Writer writer = newBufferedWriter(Paths.get(this.outputFile))) {
				printReport(report, writer);
			}
		} else {
			printReport(report, System.out);
		}
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	private void printReport(final Report report, final Appendable writer)
			  throws Exception {

		writer.append("//-------------------------------------\n");
		writer.append("Project: ").
				  append(getProject().getName()).
				  append("\n");
		writer.append("AVERAGE COVERED COMPLEXITY: ").
				  append(Double.toString(report.getAverageCoveredComplexity())).
				  append("\n");
		writer.append("\n");
		writer.append("Average Verification Ratio: ").
				  append(Double.toString(report.getAverageVerificationRatio())).
				  append("\n");
		writer.append("Average Use Case Coverage: ").
				  append(Double.toString(report.getAverageUseCaseCoverage())).
				  append("\n");
		writer.append("Average Complexity Impact: ").
				  append(Double.toString(report.getAverageComplexityImpact())).
				  append("\n");
		writer.append("\n");
      writer.append("Annotated cases: ").
            append(Long.toString(report.getNumberOfAnnotatedCases())).
            append("\n");
		writer.append("Investigated cases: ").
				  append(Integer.toString(report.getCases().size())).
				  append("\n");
		for (final TestCase tc : report.getCases()) {
			writer.append(tc.toString()).append("\n");
		}
		writer.append("//-------------------------------------\n");
	}
	/****************************************************************************
	 *
	 ***************************************************************************/
	private String testDir = null;
	private String outputFile = null;
}
