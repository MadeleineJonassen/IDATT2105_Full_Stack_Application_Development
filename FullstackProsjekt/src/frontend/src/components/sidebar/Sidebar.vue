<script>
import {collapsed, toggleSideBar, sidebarWidth} from "@/components/sidebar/state.js";
import SidebarLink from "@/components/sidebar/SidebarLink.vue";

export default {
	components: {SidebarLink},
	props: {},
	setup() {
		return{ collapsed,toggleSideBar,sidebarWidth }
	}
}
</script>

<template>
	<div class="sidebar" :style="{width: sidebarWidth}">
		<h1>
			<span v-if="collapsed">
				<div> B </div>
				<div> S </div>
			</span>
			<span v-else>
				Brain Stormer
			</span>
		</h1>

		<SidebarLink to="/" icon="fas fa-home">Home</SidebarLink>
		<SidebarLink to="/dashboard" icon="fas fa-columns">Dashboard</SidebarLink>
		<SidebarLink to="/about" icon="fas fa-chart-bar">About</SidebarLink>
		<SidebarLink to="/feedback" icon="fas fa-users">Feedback</SidebarLink>
		<SidebarLink to="/login" icon="fas fa-image">Login</SidebarLink>

		<span class="collapse-icon" :class="{'rotate-180': collapsed}" @click="toggleSideBar">
			<button> < </button>
		</span>
	</div>
</template>



<style>
:root{
	--sidebar-bd-color: #242F40;
	--sidebar-item-hover: #CCA43B;
	--sidebar-item-active: #242F40;
}
</style>
<style scoped>
.sidebar{
	color: white;
	background-color: var(--sidebar-bd-color);

	float: left;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	bottom: 0;
	padding: 0.5rem;

	transition: 0.3s ease;

	display: flex;
	flex-direction: column;
}

.collapse-icon{
	position: absolute;
	bottom: 0;
	padding: 0.7rem;

	color: white;
	transition: 0.2s linear;
}

.rotate-180{
	transform: rotate(180deg);
	transition: 0.2s linear;
}
</style>