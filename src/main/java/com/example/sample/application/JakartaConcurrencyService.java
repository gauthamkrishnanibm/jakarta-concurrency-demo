/*******************************************************************************
* Copyright (c) 2024 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v2.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v20.html
*
* Contributors:
*     IBM Corporation - initial API and implementation
*******************************************************************************/
package com.example.sample.application;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ContextService;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import jakarta.enterprise.concurrent.ManagedThreadFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
@ApplicationScoped
public class JakartaConcurrencyService {

    @Resource
    ContextService contextService;

    @Resource
    ManagedExecutorService managedExecutorService;

    @Resource
    ManagedThreadFactory managedThreadFactory;


    @GET
    @Path("managedExecuters")
    @Produces(MediaType.TEXT_PLAIN)
    public String getmanagedExecuters() throws InterruptedException, ExecutionException {
        Future future1 = managedExecutorService.submit(() -> {
            System.out.println("Job 1 running ...");
            // This takes some while
            System.out.println("Job 1 finished ...");
        });
        Future future2 = managedExecutorService.submit(() -> {
            System.out.println("Job 2 running ...");
            // This takes some while
            System.out.println("Job 2 finished ...");
        });
        System.out.println("Jobs completed");
        return "Managed Executer jobs Submitted!";
    }

    @GET
    @Path("managedThreads")
    @Produces(MediaType.TEXT_PLAIN)
    public String getManagedThreads() throws InterruptedException, ExecutionException {
        // Create and start a managed thread
        Thread thread = managedThreadFactory.newThread(() -> {
            System.out.println("Managed thread is executing");
        });
        thread.start();
        return "Managed thread created!";
    }

    @GET
    @Path("contextPropagation")
    @Produces(MediaType.TEXT_PLAIN)
    public String getContextPropagation() throws InterruptedException, ExecutionException {

        managedExecutorService.execute(() -> {
            try {
                System.out.println(new InitialContext().lookup("java:comp/env/replySuccess"));
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            }
        });
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        //Executer without context
        executor.submit(() -> {
            try {
                System.out.println(new InitialContext().lookup("java:comp/env/replySuccess"));
            } catch (NamingException namingException) {
                System.err.println("NamingException on ExecuterService as no context available.");
            }
        });

        executor.submit(contextService.contextualRunnable(()-> {
            try {
              System.out.println(new InitialContext().lookup("java:comp/env/replySuccess"));             
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            }
          }));

        return "Application context propagated with java.util.concurrent.ExecutorService using jakarta.enterprise.concurrent.ContextService";
    }

    
}
