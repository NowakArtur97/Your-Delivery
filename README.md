# Your-Delivery

## Table of Contents

- [General info](#general-info)
- [Built With](#built-with)
- [Sample Commands](#sample-commands)
- [Status](#status)

## General info

Fan-Out Serverless Architectures Using SNS, SQS and EC2 Instances built using Cloudformation resources. Applications running on EC2 instances are designed to consume messages from different queues depending on the message type.

## Built With

Build with:

- Java 17
- Spring - 3.1.0
- Gradle
- Docker
- Cloudformation

Cloudformation resources:

- SNS Topic
- SQS Queue
- SNS Subscription
- SQS Queue Policy
- VPC
- Internet Gateway
- VPC Gateway Attachment
- Route Table
- Route
- Subnet Route Table Association
- Subnet
- Security Group
- Instance Profile
- IAM Role
- EC2 Instance

## Sample Commands

Send message to Producer service:
curl -X POST -H "Content-Type: application/json" -d '{"type": "NOTIFICATION"}' http://localhost:8090/api/v1/messages
curl -X POST -H "Content-Type: application/json" -d '{"type": "INVENTORY"}' http://localhost:8090/api/v1/messages
curl -X POST -H "Content-Type: application/json" -d '{"type": "SHIPMENT"}' http://localhost:8090/api/v1/messages

Read messages from SQS queue using Consumer service:
curl -X GET http://localhost:8095/api/v1/messages

Enter EC2 instance:
ssh ec2-user@<EC2_INSTANCE_PRIVATE_IP>

## Status

Project is: finished
