<template>
	<Sidebar/>
	<div style="{'margin-left': sidebarWidth}">
		<RouterView />
	</div>
</template>


<script>
import { RouterLink, RouterView } from 'vue-router';
import { onMounted, onUnmounted, watch } from 'vue';
import { getToken, refreshAndStoreToken } from '@/tokenController.js';
import Sidebar from "@/components/shared/sidebar/Sidebar.vue"
import { sidebarWidth}  from "@/components/shared/sidebar/state.js";

export default {
  components: { RouterLink, RouterView, Sidebar },
  setup() {
    let intervalId = null;

    const startInterval = () => {
      if (!intervalId && getToken()) {
        intervalId = setInterval(async () => {
          console.log("Attempting to refresh token...");
          await refreshToken();
        }, 300000); // Refresh token every 5 minutes (300000 ms)
      }
    };

    const stopInterval = () => {
      if (intervalId) {
        clearInterval(intervalId);
        intervalId = null;
      }
    };

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
      return {sidebarWidth}
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