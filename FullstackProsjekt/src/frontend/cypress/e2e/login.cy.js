describe('Login Component', () => {
    beforeEach(() => {
        cy.visit('http://localhost:5173/login') // Assuming your login page route is '/login'
    })

    it('should display login form', () => {
        cy.get('h1#login').should('contain', 'Login')
        cy.get('input[type="text"]').should('exist')
        cy.get('input[type="password"]').should('exist')
        cy.get('input[type="submit"]').should('exist')
    })

    it('should display error message for invalid login', () => {


        cy.get('input[type="text"]').type('invalidUsername')
        cy.get('input[type="password"]').type('invalidPassword')
        cy.get('input[type="submit"]').click()


        cy.get('.error-message').should('contain', 'Error logging in, try again')
    })

    it('should redirect to profile page on successful login', () => {

        cy.get('input[type="text"]').type('123')
        cy.get('input[type="password"]').type('123')
        cy.get('input[type="submit"]').click()


        cy.url().should('include', '/profile')
    })
})
