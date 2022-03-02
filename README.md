# Order Management for Java

This workshop walks through hosting an Azure-hosted solution utlizing Azure PaaS offerings and is geared toward Java developers.

## Solution Overview

Your company has asked you to help build an order processing system in Azure. For the first portion of this project, you will rehost an existing Order Management App and Customer API that your company already uses. For the second portion of this project, you will build out an Azure Functions driven processing pipeline to complete the overall solution.

![Solution Architecture](./assets/images/architecture.png)

To accomplish this task, you will work through the following steps:

1. [Repository Setup](#repository-setup)
1. [Prepare you development environment](#dev-environment-setup)
1. [Set up common Azure resources](#set-up-common-azure-resources)
1. [Deploy the Order Management App](#deploy-the-order-management-app)
1. [Deploy the Customer API and Database](#deploy-the-customer-api-and-database)
1. Set up the Order API resources and database.
1. Create the Order API Function App and deploy it.
1. Set up the Order Processing resources, modify the Function App, and deploy it.

## Repository Setup

If the origin repository has not been forked into your personal GitHub account or your Orgnaization's GitHub account, please do so before completing any additional steps.

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
    1. In the [Azure Portal](https://portal.azure.com), navigate to your Storage Account
    1. In the side menu select ***Data management > Static website***
    1. Toggle the Static website to **Enabled**
    1. Set the site information
        * Index document name: index.html
        * Error document path: index.html
1. Deploy the application to your Storage Account
    1. In your Codespace, right click on **order-management-app** and select **Deploy to Static Website via Azure Storage...**
    1. Select your storage account
1. Add the Application Insights information to the App
    1. Navigate to the App (<https://[storageaccountname>].z13.web.core.windows.net)
    1. Select the settings Cog in the top right corner of the app
    1. Add the Application Insights connection string to the settings

## Deploy the Customer API and Database

The Customer API is a Spring API that interacts with Customer database to track customer information. We will host the API in Azure App Service and the database in Azure SQL Database.

1. Provision and deploy the Customer database
    1. In the [Azure Portal](https://portal.azure.com), create an Azure SQL Database
        * Database name: customer-sqldb
        * Server: Create New
            * Server Name: customer-/[uniquename/]-sqlsvr
            * Server admin login: customeradmin
            * Password: ABCD1234abcd!
            * Confirm password: ABCD1234abcd!
        * Compute + storage: Configure Database
            * Service tier: Basic
        * Backup storage redundancy: Locally-redundant backup storage
    1. Update the Customer database schema
        1. In the [Azure Portal](https://portal.azure.com), navigate to the Customer database
        1. In the side menu, select ***Query editor***
        1. Try to log in with your credentials:
            * Login: customeradmin
            * Password: ABCD1234abcd!
        1. If you receive a networking access error, click the link at the end to add your IP address to the whitelist rules.
        1. In the query editor, run the following SQL:

            ```sql
            CREATE TABLE Customers (
                Id UNIQUEIDENTIFIER NOT NULL,
                Name NVARCHAR(100) NOT NULL,
                CONSTRAINT PK_Customers_Id PRIMARY KEY CLUSTERED (Id)
            );
            ```

1. Provision and deploy the Customer API
    1. In the [Azure Portal](https://portal.azure.com), create an Azure App Service Web App
        * Name: customer-api-/[uniquename/]-app
        * Runtime stack: Java 11
        * Linux Plan: Create New
            * Name: customer-api-plan
        * Sku and size: Dev/Test B1
    1. Update the Web App settings with the database information
        1. Navigate to the App Service Web App
        1. In the side menu, select ***Settings > Configuration*** and add the following Application Settings
            * DB_SERVER_NAME: customer-/[uniquename/]-sqlsvr
            * DB_NAME: customer-sqldb
            * DB_USERNAME: customeradmin
            * DB_PASSWORD: ABCD1234abcd!
    1. Deploy the Customer API to the Azure App Service Web App
        1. In your Codespace, open up a new Terminal ***Terminal > New Terminal***
        1. In the terminal build the API with Maven

            ```bash
            cd customer-api
            mvnw package
            ```

        1. In the file explorer, right click on ***customer-api > target > customer-api-0.0.1-SNAPSHOT.jar*** and select **Deploy to Web App**
        1. Select your Subscription and Web App
    1. Troubleshoot your Customer API
        1. Navigate to your Customer API in your browser
        1. In the [Azure Portal](https://portal.azure.com), navigate to your App Service ***Monitoring > Log stream*** to identify the issue
        1. In the Azure SQL Server resource, navigate to ***Security > Firewalls and virtual networks***
            * Allow Azure services and resources to access this server: Yes
        1. Navigate to your Customer API again
