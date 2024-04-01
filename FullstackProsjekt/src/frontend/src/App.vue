<script>
import { RouterLink, RouterView } from 'vue-router'
import Sidebar from "@/components/sidebar/Sidebar.vue"
import { sidebarWidth}  from "@/components/sidebar/state.js";
export default {
	components:{Sidebar},
	setup(){
		return {sidebarWidth}
	}
	};
</script>

<template>
	<Sidebar/>
	<div style="{'margin-left': sidebarWidth}">
		<RouterView />
	</div>
</template>

