<script>
import { RouterLink, RouterView } from 'vue-router';
import { onMounted, onUnmounted, watch } from 'vue';
import { getToken, setToken, removeToken, refreshAndStoreToken } from '@/tokenController.js';

export default {
  components: { RouterLink, RouterView },
  setup() {
    let intervalId = null;

    // Start interval for token refresh
    const startInterval = () => {
      if (!intervalId && getToken()) {
        intervalId = setInterval(async () => {
          console.log("Attempting to refresh token...");
          await refreshToken();
        }, 6000*5); // Refresh token every 5 seconds
      }
    };

    // Stop interval for token refresh
    const stopInterval = () => {
      if (intervalId) {
        clearInterval(intervalId);
        intervalId = null;
      }
    };

    // Refresh token
    const refreshToken = async () => {
      try {
        const existingToken = getToken();
        if (existingToken) {
          const newToken = await refreshAndStoreToken(existingToken);
          if (newToken !== existingToken) {
            console.log("New token: ", newToken);
          } else {
            console.log("Token did not need to be refreshed just yet.");
          }
        } else {
          console.error("No existing token found.");
        }
      } catch (error) {
        console.error("Error refreshing token: ", error);
      }
    };

    // Lifecycle hooks
    onMounted(() => {
      startInterval();
    });

    onUnmounted(() => {
      stopInterval();
    });

    // Watch for changes in token
    watch(() => getToken(), (newToken, oldToken) => {
      if (newToken !== oldToken) {
        stopInterval();
        startInterval();
      }
    });
  }
};
</script>
