package br.com.cds.connecta.presenter.bean.analysis.xml;

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
public class XMLNodeParser {

    public XMLNode parseNode(Node node) {
        XMLNode xmlNode = new XMLNode();

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

    public Map<String, XMLNode> parseNodeMap(NamedNodeMap attributes) {
        Map<String, XMLNode> map = new HashMap<>(attributes.getLength());

        for (int i = 0; i < attributes.getLength(); i++) {
            Node item = attributes.item(i);
            //para que o namespace nao seja sdddgcriado
            if (!item.getNodeName().equals("xmlns")) {
                map.put(item.getNodeName(), parseNode(item));
            }
        }
        return map;
    }

    public List<XMLNode> parseNodeList(NodeList childNodes) {
        List<XMLNode> list = new ArrayList<>();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            XMLNode xmlNode = parseNode(item);
            //para evitar Nodes vazios #text
            if (xmlNode.getNodeType() != 3) {
                list.add(xmlNode);
            }
        }
        return list;
    }
}
