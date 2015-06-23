/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.bean.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author diego
 */
public class ParseBeanNode {

    public XMLNodeBean parseNode(Node node) {
        XMLNodeBean xmlNode = new XMLNodeBean();

        if (node.getAttributes() != null && node.getAttributes().getLength() > 0) {
            xmlNode.setAttributes(parseNodeMap(node.getAttributes()));
        }

        xmlNode.setNodeName(node.getNodeName());
        xmlNode.setNodeType(node.getNodeType());
        xmlNode.setNodeValue(node.getNodeValue());
        xmlNode.setPrefix(node.getPrefix());
        xmlNode.setTextContent(node.getTextContent());

        if (node.getChildNodes() != null && node.getChildNodes().getLength() > 0) {
            xmlNode.setChildNodes(parseNodeList(node.getChildNodes()));
        }

        return xmlNode;
    }

    public Map<String, XMLNodeBean> parseNodeMap(NamedNodeMap attributes) {
        Map<String, XMLNodeBean> map = new HashMap<>(attributes.getLength());

        for (int i = 0; i < attributes.getLength(); i++) {
            Node item = attributes.item(i);
            //para que o namespace nao seja sdddgcriado
            if (!item.getNodeName().equals("xmlns")) {
                map.put(item.getNodeName(), parseNode(item));
            }
        }
        return map;
    }

    public List<XMLNodeBean> parseNodeList(NodeList childNodes) {
        List<XMLNodeBean> list = new ArrayList<>();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            XMLNodeBean xmlNode = parseNode(item);
            //para evitar Nodes vazios #text
            if (xmlNode.getNodeType() != 3) {
                list.add(xmlNode);
            }
        }
        return list;
    }
}
