import { ref } from 'vue';

export const user = ref({
    userId: null,
    email: null,
});

// Function to set user data
export function setUser(userId, userEmail) {
    user.value = {
        userId: userId,
        email: userEmail
    };
}
export function clearUser() {
    user.value = {
        userId: null,
        email: null,
    };
}