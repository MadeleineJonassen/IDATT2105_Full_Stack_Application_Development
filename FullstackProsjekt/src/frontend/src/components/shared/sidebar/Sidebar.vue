<template>
	<div class="sidebar" :style="{width: sidebarWidth}">

		<SidebarLink to="/" icon="home-icon">Home</SidebarLink>
		<SidebarLink to="/dashboard" icon="dashboard-icon">Dashboard</SidebarLink>
		<SidebarLink to="/about" icon="about-us-icon">About</SidebarLink>
		<SidebarLink to="/feedback" icon="feedback-icon">Feedback</SidebarLink>
		<SidebarLink to="/login" icon="login-icon">Login</SidebarLink>
		<SidebarLink v-if="isLoggedIn" to="/profile" icon="profile-icon">Profile</SidebarLink>

		<span class="collapse-icon" :class="{'rotate-180': collapsed}" @click="toggleSideBar">
			<Svg name="double-arrow" class="sidebar-c-icon"/>
		</span>

	</div>
</template>


<script>
import { collapsed, toggleSideBar, sidebarWidth } from "@/components/shared/sidebar/state.js";
import SidebarLink from "@/components/shared/sidebar/SidebarLink.vue";
import Svg from "@/assets/Svg.vue";
import {ref} from "vue";

export default {
	components: {Svg, SidebarLink},
	props: {},
	setup() {
		const isLoggedIn = ref(false);

		const handleClickOutside = (event) => {
			const sidebar = document.querySelector('.sidebar');
			if (sidebar && !sidebar.contains(event.target)) {
				collapsed.value = true; // Collapse the sidebar
			}
		}
		document.addEventListener('click', handleClickOutside);
		const beforeUnmount = () => {
			document.removeEventListener('click', handleClickOutside);
		};
		return { collapsed, toggleSideBar, sidebarWidth, beforeUnmount, isLoggedIn };
	}
}
</script>


<style>

</style>
<style scoped>
.sidebar{
	color: var(--text-light-color);
	background-color: var(--sidebar-bd-color);
	float: left;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	bottom: 0;
	padding: 0.5rem;
	transition: 0.25s ease;
	display: flex;
	flex-direction: column;
	margin-left: -38px;
}
.collapse-icon{
	position: absolute;
	bottom: 0;
	margin-left: 50px;
	transition: 0.2s linear;
}
.sidebar-c-icon{
	height: 30px;
	width: 30px;
}
.rotate-180{
	transform: rotate(180deg);
	transition: 0.2s linear;
}
</style>