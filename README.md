# Order Management for Java

This workshop walks through hosting an Azure-hosted solution utlizing Azure PaaS offerings and is geared toward Java developers.

## Solution Overview

Your company has asked you to help build an order processing system in Azure. For the first portion of this project, you will rehost an existing Order Management App and Customer API that your company already uses. For the second portion of this project, you will build out an Azure Functions driven processing pipeline to complete the overall solution.


![Solution Architecture](./assets/images/architecture-dark.png#gh-dark-mode-only)
![Solution Architecture](./assets/images/architecture-light.png#gh-light-mode-only)

To accomplish this task, you will work through the following steps:

1. Prepare you development environment.
1. Set up common resources.
1. Set up the Order Management App resources and deploy the app.
1. Set up the Customer API resources and database and deploy the API.
1. Set up the Order API resources and database.
1. Create the Order API Function App and deploy it.
1. Set up the Order Processing resources, modify the Function App, and deploy it.

## Preparing your Development Environment

To work effectively you'll need a fully functioning development environment. To keep from having to install a lot of dependencies, a GitHub codespace has been configured for your use. (These steps assume that the original repo has been forked into your GitHub organization.) Follow the steps below to set up your environment:

1. Create a new branch with your name or a custom identifier:
  ![Branch Creation](./assets/images/create-branch.png#gh-dark-mode-only)
  ![Branch Creation](./assets/images/create-branch.png#gh-light-mode-only)
1. 