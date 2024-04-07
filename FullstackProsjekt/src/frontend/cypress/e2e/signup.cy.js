describe('Signup Component', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5173/signup')
    })

    it('should display signup form', () => {
        cy.get('h1#signup').should('contain', 'Signup')
        cy.get('input[type="text"]').should('exist')
        cy.get('input[type="password"]').should('exist')
        cy.get('.submit-btn').should('exist')
    })

    it('should show error message for invalid signup', () => {
        cy.server()
        cy.route({
            method: 'POST',
            url: '/auth/register',
            status: 400,
            response: { message: 'Username already exists' }
        }).as('signupRequest')

        cy.get('input[type="text"]').type('existingUsername')
        cy.get('input[type="password"]').type('password')
        cy.get('.submit-btn').click()

        cy.wait('@signupRequest')

        cy.get('.error-message').should('contain', 'Error signing up, try again')
    })

    it('should redirect to login page on successful signup', () => {
        cy.server()
        cy.route('POST', '/auth/register', {}).as('signupRequest')

        cy.get('input[type="text"]').type('newUsername')
        cy.get('input[type="password"]').type('password')
        cy.get('.submit-btn').click()

        cy.wait('@signupRequest')

        cy.url().should('include', '/login')
    })
})
