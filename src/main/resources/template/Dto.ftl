package com.${service_name}.model;


import lombok.Data;

import java.util.*;

import java.io.Serializable;

@Data
public class ${entity_name}Dto implements Serializable {

<#list fields as field>
    private ${field.type.simpleName} ${field.name};
</#list>
}
