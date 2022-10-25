#parse("Hlag macros.java")
################## Mapper #####################
#macro(renderMapperFields $method)
    #foreach($param in $method.getMethodParams())
    #foreach($type in $param.getType().getComposedTypes())
        #if (!$type.isEnum())

        #end
        #if ($type.isEnum())
            #foreach($field in $type.getFields())
                #if($type.getCanonicalName() == $field.getType().getCanonicalName())
                  
                    #end
            #end
        #end
    #end
#end
#end

################## Instance #####################
#macro(createInstance $fieldType)
// test
#if($fieldType == "java.util.List")
new ArrayList();
#else
new $fieldType();
#end
#end