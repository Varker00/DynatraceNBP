# Backend oriented task (Java)

## Description
This project is the implementation of Dynatrace's Backend oriented task. 
The goal of the project is to retrieve data concerning currency exchange, 
buy and ask rates, and performsome  operations on it.

Detailed description available at https://github.com/joaquinfilipic-dynatrace/gdn-internship-2023


## Installation
1. Clone the repository to your local machine
2. Navigate to the project directory in your terminal
3. Run the following commands:


    docker build --tag dynatracenbs .
The first command will build the docker image of the project

    docker run -p 8080:8080 dynatracenbs

While the second will start the container running the app.

The application will be available at `http://localhost:8080`


## Usage
The app exposes 3 endpoints, all of which are available using any HTTP client.

A swagger UI is also available at `http://localhost:8080/swagger-ui/index.html`

### Average exchange rate for a currency on given day
This endpoint is available at `http://localhost:8080/api/avgRate/{code}/{date}`, 
where `{code}` is a currency's three-letter code, and `{date}` is a day in a `YYYY-MM-DD` format.

The response consists of the average exchange rate for that currency, on that day.

#### Example usage:
Average exchange rate for the british pound on the 5th of May 2023:

    curl http://localhost:8080/api/avgRate/gbp/2023-04-05
#### Expected response:
    {"avgRate":5.3379}

### Minimum and maximum exchange rate for a currency
This endpoint is available at `http://localhost:8080/api/minMax/{code}/{n}`,
where `{code}` is a currency's three-letter code, and `{n}` is a number (between 1 and 255) of most recent quotations to retrieve.

The response consists of the minimum and maximum exchange rate for that currency
in the specified amount of most recent quotations.

#### Example usage:
Smallest and largest exchange rate for the british pound in the last 100 records

    curl http://localhost:8080/api/minMax/gbp/100
#### Expected response:
    {"minAvgValue":5.1958,"maxAvgValue":5.4638}

### Maximum difference between asking and bidding price for a currency
This endpoint is available at `http://localhost:8080/api/maxDiff/{code}/{n}`,
where `{code}` is a currency's three-letter code, and `{n}` is a number (between 1 and 255) of most recent quotations to retrieve.

The response consists of the largest difference between the asking and bidding price for a currency in the specified amount of most recent quotations.

#### Example usage:
Largest difference between the asking and bidding price for the british pound in the last 100 records

    curl http://localhost:8080/api/minMax/gbp/100
#### Expected response:
    {"maxDifference":0.10959999999999948}




