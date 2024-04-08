describe('Signup Component', () => {
  beforeEach(() => {
    // Assuming your signup page route is '/signup'
    cy.visit('http://localhost:5173/signup')
  })

  it('should display signup form', () => {
    cy.get('h1#signup').should('contain', 'Signup')
    cy.get('input[type="text"]').should('exist')
    cy.get('input[type="password"]').should('exist')
    cy.get('.submit-btn').should('exist')
  })

  it('should show error message for invalid signup', () => {
    cy.intercept('POST', '/auth/register', {
      statusCode: 400,
      body: { message: 'Username already exists' }
    }).as('signupRequest')

    cy.get('input[type="text"]').type('123')
    cy.get('input[type="password"]').eq(0).type('password') 
    cy.get('input[type="password"]').eq(1).type('password') 
    cy.get('.submit-btn').click()


    cy.get('.error-message').should('contain', 'Error signing up, try again')
  })

  it('should redirect to login page on successful signup', () => {
    cy.intercept('POST', '/auth/register', {
      statusCode: 200,
      body: {}
    }).as('signupRequest')

    cy.get('input[type="text"]').type('Banan')
    cy.get('input[type="password"]').eq(0).type('password')
    cy.get('input[type="password"]').eq(1).type('password')
    cy.get('.submit-btn').click()


    cy.url().should('include', '/login')
  })
})
