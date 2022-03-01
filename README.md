# Order Management for Java

This workshop walks through hosting an Azure-hosted solution utlizing Azure PaaS offerings and is geared toward Java developers.

## Solution Overview

Your company has asked you to help build an order processing system in Azure. For the first portion of this project, you will rehost an existing Order Management App and Customer API that your company already uses. For the second portion of this project, you will build out an Azure Functions driven processing pipeline to complete the overall solution.

![Solution Architecture](./assets/images/architecture.png)

To accomplish this task, you will work through the following steps:

1. [Prepare you development environment](#dev-environment-setup)
1. [Set up common Azure resources](#set-up-common-azure-resources)
1. [Deploy the Order Management App](#)
1. Set up the Customer API resources and database and deploy the API.
1. Set up the Order API resources and database.
1. Create the Order API Function App and deploy it.
1. Set up the Order Processing resources, modify the Function App, and deploy it.

## Dev Environment Setup

To work effectively you'll need a fully functioning development environment. To keep from having to install a lot of dependencies, a GitHub codespace has been configured for your use. (These steps assume that the original repo has been forked into your GitHub organization.) Follow the steps below to set up your environment:

1. In GitHub, create a new branch with your name or a custom identifier.
1. Go to ***Code > New codespace*** to open up a development environment for your new branch.

## Set Up common Azure Resources

For this project, you will have a number of Azure resources used for common concerns such as logging and file storage. In this section, we will set up those common resources so they can be used later in our project.

1. Navigate to the [Azure portal](https://portal.azure.com/)
1. Create a new Resource Group to hold all of your Order Management Azure resources. ***NOTE: The Subscription, Resource Group, and Region used/created in this step should be used for all other resources being creating***
    * Subscription: Your subscription
    * Name: order-management-\[uniquename\]-rg
1. Create a new Log Analytics Workspace to hold all of your logs
    * Name: order-management-\[uniquename\]-log
1. Create an Application Insights resource to capture application logs and metrics
    * Name: order-management-\[uniquename\]-ai
    * Resource Mode: Workspace-based
    * Log Analytics Workspace: order-management-\[uniquename\]-log
1. Create a Storage Account to hold files and assets
    * Name: ordermanagement\[uniquename\]st
    * Redundancy: Locally-redundant storage (LRS)

## Deploy the Order Management App

The Order Management App is a Single Page Application (Angular) that has been transpiled into static assets for hosting. For our purposes, we will host the App as a Static Website from our Storage Account for web access.

1. Turn on Static Website hosting for your Storage Account
    1. In the Azure Portal, navigate to your Storage Account
    1. In the side menu select ***Data management > Static website***
    1. Toggle the Static website to **Enabled**
    1. Index document name: index.html
    1. Error document path: index.html
1. Deploy the application to your Storage Account
    1.
