package br.com.cds.connecta.presenter.bean.analysis.xml;

import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public class XMLNode {
    private Map<String, XMLNode> attributes;
    private String nodeName;
    private short nodeType;
    private String nodeValue;
    private String prefix;
    private String textContent;
    private List<XMLNode> childNodes;
    private Boolean isTable;

    public void setAttributes(Map<String, XMLNode> attributes) {
        this.attributes = attributes;
    }

    public Map<String, XMLNode> getAttributes() {
        return attributes;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setNodeType(short nodeType) {
        this.nodeType = nodeType;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setChildNodes(List<XMLNode> childNodes) {
        this.childNodes = childNodes;
    }

    public String getNodeName() {
        return nodeName;
    }

    public short getNodeType() {
        return nodeType;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getTextContent() {
        return textContent;
    }

    public List<XMLNode> getChildNodes() {
        return childNodes;
    }

    public Boolean getIsTable() {
        return isTable;
    }

    public void setIsTable(Boolean isTable) {
        this.isTable = isTable;
    }
    
}
