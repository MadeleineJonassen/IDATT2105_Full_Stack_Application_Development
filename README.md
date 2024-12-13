# BrainStormer
### Description
BrainStormer is a full-stack web application developed for the IDATT2105 course. It provides a comprehensive and user-friendly platform for creating, managing, and taking quizzes for educational, training, or entertainment purposes. Inspired by popular quiz platforms like Quizlet, Kahoot, and Quizizz, BrainStormer aims to offer a similar experience with its own set of unique features. I wrote the frontend of this application.

## Highlights

<p align="center">
  <img src="https://github.com/user-attachments/assets/9333815d-1aef-4b88-a066-489528f457ea" alt="Image 1" width="300" style="margin-right: 10px;">
  <img src="https://github.com/user-attachments/assets/bf39ad05-3a09-46ca-a605-9e0f0757af07" alt="Image 2" width="300">
</p>


## Features
- User Authentication
- Quiz Creation
- Tagging and Categorization
- Search and Filter
- Question Management
- Quiz Templates
- Collaboration
- Import and Export
- Randomization
- Scoring and Feedback
- Progress Tracking
- Feedback and Support

## Running the application
To run the back-end, run FullstackApplication.java (FullstackProsjekt/src/backend/main/java/edu/ntnu/idatt2105/FullstackProsjektApplication.java). 
To run the front end, move to FullstackProsjekt/src/frontend and run the following commands:
```sh
npm install
```
then
```sh
npm run build

```


### Run Unit Tests with Vitest
```sh
npm run test:unit
```

### Run End-to-End Tests with Cypress
```sh
npm run test:e2e:dev
```

This runs the end-to-end tests against the Vite development server.
It is much faster than the production build.

But it's still recommended to test the production build with test:e2e before deploying (e.g. in CI environments):
```sh
npm run build
npm run test:e2e
```

### Lint with ESLint
```sh
npm run lint

```

## Usage
1. Register or log in to your account.
2. Create a quiz by adding questions, specifying categories, and setting difficulty levels.
4. Take quizzes created by other users or yourself.
5. Explore the various features offered by BrainStormer to enhance your quiz experience.


## Authors and Acknowledgment
- Torbjørn Antonsen
- Heine Mærde Brakstad 
- Krisitane Skogvang Kolshus
- Madeleine Stenberg Jonassen
