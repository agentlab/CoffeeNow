/**
 *
 */
package ru.agentlab.coffeenow.commandsparser;

import java.util.HashSet;
import java.util.Set;

import org.hypergraphdb.HyperGraph;
import org.osgi.service.component.annotations.Component;

/**
 * @author avk
 *
 */

@Component
public class UserCommandParser {

    private Set<String> whyStrings = new HashSet<>();

    static final String dbLocation = "./HGTestDB"; //$NON-NLS-1$

    public UserCommandParser() {
        System.out.println("Hello, World!"); //$NON-NLS-1$

        whyStrings.add("why"); //$NON-NLS-1$
        whyStrings.add("why?"); //$NON-NLS-1$

    }

    public void parseCommand(final String input) {
        // Hello, world
        if (whyStrings.contains(input.toLowerCase()))
        {
            getWhyInfoFromDataBase();
        }
    }

    private void getWhyInfoFromDataBase() throws UnsupportedOperationException {
//        HyperGraph graph = null;
        try (HyperGraph graph = new HyperGraph(dbLocation))
        {
            String hello = graph.get(graph.add("Hello World")); //$NON-NLS-1$
            System.out.println(hello.toLowerCase());
            graph.close();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }

        throw new UnsupportedOperationException();
    }

}
