package org.sampledsu.web;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 *
 */
public class Main {
	private static String DEFAULT_DIR = "src/main/webapp/";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        String webappDirLocation = DEFAULT_DIR;
        if ( args.length > 0 ) webappDirLocation = args[0];

        // The port that we should run on can be set into an environment variable
        // Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        Server server = new Server(Integer.valueOf(webPort));
        WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        //root.setResourceBase(webappDirLocation);
        
        ResourceCollection resources = new ResourceCollection(new String[] {
        		webappDirLocation,
        		webappDirLocation + "/static"
        });
        root.setBaseResource(resources);

        // Parent loader priority is a class loader setting that Jetty accepts.
        // By default Jetty will behave like most web containers in that it will
        // allow your application to replace non-server libraries that are part of the
        // container. Setting parent loader priority to true changes this behavior.
        // Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
        root.setParentLoaderPriority(true);

        /*ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase( webappDirLocation + "/static" );

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, root });
        server.setHandler(handlers);
        */
        
        server.setHandler(root);

        server.start();
        server.join();
    }

}
