#parse("TestMe macros.java")
#parse("Hlag macros.java")
#set($hasMocks=$MockitoMockBuilder.hasMockable($TESTED_CLASS.fields) && $TESTED_CLASS.isInterface)
#if($PACKAGE_NAME)
package ${PACKAGE_NAME};
#end

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
#if($hasMocks)
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;
#end
#parse("File Header.java")
class ${CLASS_NAME} {
#renderMockedFields($TESTED_CLASS.fields)
#renderTestSubjectInit($TESTED_CLASS,$TestSubjectUtils.hasTestableInstanceMethod($TESTED_CLASS.methods),$hasMocks)

#if($hasMocks)
    @BeforeEach
    void setUp() {
        MockitoAnnotations.${MockitoMockBuilder.initMocksMethod}(this);
    }
#end
#foreach($method in $TESTED_CLASS.methods)
    @Test
    void #renderTestMethodName($method.name)() {
    #renderMapperFields($method)
    #if($MockitoMockBuilder.shouldStub($method,$TESTED_CLASS.fields))
        #renderMockStubs($method,$TESTED_CLASS.fields)
        #renderMethodCall($method,$TESTED_CLASS.name)
        #if($method.hasReturn())        Assertions.#renderJUnitAssert($method)#end
    }
#end
#end
}