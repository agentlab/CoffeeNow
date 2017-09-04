/**
 *
 */
package org.hypergraphdb.examples;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HyperGraph;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author amivanoff
 * <p>
 * Example OSGi DS component implementation class
 * <p>
 * For HGDB Getting Started see {@link <a href="https://github.com/hypergraphdb/hypergraphdb/wiki/GettingStarted">GettingStarted Wiki</a>}
 */
@Component
public class TestComponentImpl {

	static final String dbLocation = "./HGTestDB"; //$NON-NLS-1$

	@Activate
	public void start() {

		Book myBook = new Book("Critique of Pure Reason", "E. Kant"); //$NON-NLS-1$ //$NON-NLS-2$
		Book myBook2 = new Book("222Critique of Pure Reason", "222E. Kant"); //$NON-NLS-1$ //$NON-NLS-2$
		myBook2.setBook(myBook);

		// with try-with-resources
		try (HyperGraph graph = new HyperGraph(dbLocation)) {
			HGHandle stringHandle, bookHandle, bookHandle2, arrayHandle;

			String x = "Hello World"; //$NON-NLS-1$
			stringHandle = graph.add(x);
			graph.add("Hello World 2"); //$NON-NLS-1$

			bookHandle = graph.add(myBook);
			bookHandle2 = graph.add(myBook2);

			arrayHandle = graph.add(new double[] { 0.9, 0.1, 4.3434 });

			// myBook was created and previously added to the database.
			// Now, we will update one of the existing object's attributes:
			myBook.setYearPublished(1988);
			graph.update(myBook);

			// ...

			// Now, we need to delete the object from the database.
			//graph.remove(bookHandle);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}

		// without try-with-resources
		HyperGraph graph = new HyperGraph(dbLocation);
		try {
			System.out.println("Query"); //$NON-NLS-1$
			// Querying is done conveniently by using the static helper class "hg"
			for (Object s : hg.getAll(graph, hg.type(String.class))) {
				System.out.println(s);
			}

			for (Object s : hg.getAll(graph, hg.type(Book.class))) {
				System.out.print("book: "); //$NON-NLS-1$
				System.out.println(s);
				if(s instanceof Book) {
					System.out.print("\tsub-book: "); //$NON-NLS-1$
					System.out.println(((Book)s).getBook());
					((Book)s).setYearPublished(2000);
				}
			}

			for (Object s : hg.getAll(graph, hg.type(Book.class))) {
				System.out.print("book: "); //$NON-NLS-1$
				System.out.println(s);
				if(s instanceof Book) {
					System.out.print("\tsub-book: "); //$NON-NLS-1$
					System.out.println(((Book)s).getBook());
				}
			}
		}
		finally {
			graph.close();
		}
	}
}
