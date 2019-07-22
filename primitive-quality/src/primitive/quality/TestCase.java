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

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import static java.nio.file.Files.walk;
import java.nio.file.Path;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import org.junit.Test;

/*******************************************************************************
 *@author lukasz.bownik@gmail.com
 ******************************************************************************/
final class TestCase {
   /****************************************************************************
    *
    ***************************************************************************/
   TestCase(final Method method) {

      this.method = method;
   }
   /****************************************************************************
    *
    ***************************************************************************/
   public String getComment() {

      return isAnnotated() ? getQuality().comment() : "";
   }
   /****************************************************************************
    *
    ***************************************************************************/
   public double getVerificationRatio() {

      return isAnnotated() ? getQuality().verificationRatio() : 0.0;
   }
   /****************************************************************************
    *
    ***************************************************************************/
   public double getUseCaseCoverage() {

      return isAnnotated() ? getQuality().useCaseCoverage() : 0.0;
   }
   /****************************************************************************
    *
    ***************************************************************************/
   public double getComplexityImpact() {

      return isAnnotated() ? getQuality().complexityImpact() : 0.0;
   }
   /****************************************************************************
    *
    ***************************************************************************/
   private Quality getQuality() {

      return this.method.getAnnotation(Quality.class);
   }
   /****************************************************************************
    *
    ***************************************************************************/
   public double getCoveredComplexity() {

      return getVerificationRatio() * getUseCaseCoverage() * getComplexityImpact();
   }
   /****************************************************************************
    *
    ***************************************************************************/
   public boolean isAnnotated() {

      return this.method.isAnnotationPresent(Quality.class);
   }
   /****************************************************************************
    *
    ***************************************************************************/
   public Method getMethod() {

      return this.method;
   }
   /****************************************************************************
    *
    ***************************************************************************/
   @Override
   public int hashCode() {

      return this.method.hashCode();
   }
   /****************************************************************************
    *
    ***************************************************************************/
   @Override
   public boolean equals(final Object obj) {

      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      return this.method.equals(((TestCase) obj).method);
   }
   /****************************************************************************
    *
    ***************************************************************************/
   @Override
   public String toString() {

      return "TestCase{" + "method=" + getMethod() + " verificationRatio="
            + getVerificationRatio() + " useCaseCoverage=" + getUseCaseCoverage()
            + " complexityImpact=" + getComplexityImpact()
            + " comment=" + getComment() + "}";
   }

   /****************************************************************************
    *
    ***************************************************************************/
   public static List<TestCase> testCasesOf(final Path path) throws Exception {

      try (final URLClassLoader loader = new URLClassLoader(new URL[]{path.toUri().toURL()})) {
         return walk(path).
               map(p -> path.relativize(p)).
               map(Path::toString).
               filter(s -> s.endsWith(".class")).
               map(s -> s.replace(".class", "")).
               map(s -> s.replace(path.getFileSystem().getSeparator(), ".")).
               map(s -> loadClass(loader, s)).
               flatMap(TestCase::testCasesOf).
               collect(toList());
      }
   }
   /****************************************************************************
    *
    ***************************************************************************/
   private static Stream<TestCase> testCasesOf(final Class<?> cls) {

      return Stream.of(cls.getDeclaredMethods()).
            filter(m -> m.isAnnotationPresent(Test.class)).
            map(TestCase::new);
   }
   /****************************************************************************
    *
    ***************************************************************************/
   private static Class<?> loadClass(final ClassLoader loader, final String name) {

      try {
         return loader.loadClass(name);
      } catch (final Exception e) {
         throw new RuntimeException(e);
      }
   }
   /****************************************************************************
    *
    ***************************************************************************/
   private final Method method;
}
