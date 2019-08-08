# primitive-quality

An Apache Ant task automatically calculating metrics descibed in 

[Unit Test Suite Quality Estimation](https://www.codeproject.com/Articles/4051293/Unit-Test-Suite-Quality-Estimation).

Add *primitive-quality.jar* to your test path (to use @Quality anotation) and build path (to use ACC task).
The jar available at [central Maven repository](https://mvnrepository.com/artifact/com.github.lbownik/primitive-quality).

Report example:
```
//-------------------------------------
Project: Quality
AVERAGE COVERED COMPLEXITY: 0.22666666666666668

Average Verification Ratio: 0.5666666666666667
Average Use Case Coverage: 0.5
Average Complexity Impact: 0.3333333333333333

Annotated cases: 4
Investigated cases: 6
TestCase{method=public void primitive.quality.data.ProperTestClass1.properTest1() verificationRatio=1.0 useCaseCoverage=0.5 complexityImpact=0.1 comment=Comment1}
TestCase{method=public void primitive.quality.data.ProperTestClass1.improperTest2() verificationRatio=0.0 useCaseCoverage=0.0 complexityImpact=0.0 comment=}
TestCase{method=public void primitive.quality.data.ProperTestClass1.properTest2() verificationRatio=0.7 useCaseCoverage=1.0 complexityImpact=0.9 comment=Comment2}
TestCase{method=public void primitive.quality.data.ProperTestClass2.properTest3() verificationRatio=1.0 useCaseCoverage=0.5 complexityImpact=0.1 comment=Comment3}
TestCase{method=public void primitive.quality.data.ProperTestClass2.properTest4() verificationRatio=0.7 useCaseCoverage=1.0 complexityImpact=0.9 comment=Comment4}
TestCase{method=public void primitive.quality.data.ProperTestClass2.improperTest4() verificationRatio=0.0 useCaseCoverage=0.0 complexityImpact=0.0 comment=}
//-------------------------------------
```

To use Ant task:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project name="Quality" default="quality" basedir=".">
    <taskdef name="acc" classname="primitive.quality.ACC"/>

    <target name="quality">
        <acc testDir="../primitive-quality.test.data.proper/build/classes"
             outputFile="build/test/output.txt"/>
    </target>
</project>
```
Usage: 
* "testDir" attribute specifies path to compiled unit test classes, 
* "outputFile" attribute specifies path to report file (skip it to print to standard output).