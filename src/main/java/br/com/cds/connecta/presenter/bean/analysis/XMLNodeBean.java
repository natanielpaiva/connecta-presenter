package br.com.cds.connecta.presenter.bean.analysis;

import java.util.List;
import java.util.Map;

/**
 *
 * @author diego
 */
public class XMLNodeBean {
    private Map<String, XMLNodeBean> attributes;
    private String nodeName;
    private short nodeType;
    private String nodeValue;
    private String prefix;
    private String textContent;
    private List<XMLNodeBean> childNodes;
    private Boolean isTable;

    public void setAttributes(Map<String, XMLNodeBean> attributes) {
        this.attributes = attributes;
    }

    public Map<String, XMLNodeBean> getAttributes() {
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

    public void setChildNodes(List<XMLNodeBean> childNodes) {
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

    public List<XMLNodeBean> getChildNodes() {
        return childNodes;
    }

    public Boolean getIsTable() {
        return isTable;
    }

    public void setIsTable(Boolean isTable) {
        this.isTable = isTable;
    }
    
}
