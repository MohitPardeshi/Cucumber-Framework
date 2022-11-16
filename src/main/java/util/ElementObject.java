package util;

import java.util.LinkedHashMap;
import java.util.Map;

public class ElementObject {
    private String element;
    private String accessType;
    private String accessName;

    {
        this.element=null;
        this.accessType=null;
        this.accessName=null;
    }
    /**
     * Setting the Object from the Tree Map
     * @param element
     */
    public ElementObject(String element){
        String[] accessTypeAccessName=PageObjectGenerator.getAccessNameAccessType(element);
        this.element=element;
        this.accessType=accessTypeAccessName[0];
        this.accessName=accessTypeAccessName[1];
    }


    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }
}
