<script>
import { collapsed, toggleSideBar, sidebarWidth } from "@/components/shared/sidebar/state.js";
import SidebarLink from "@/components/shared/sidebar/SidebarLink.vue";
import Svg from "@/assets/Svg.vue";

export default {
	components: {Svg, SidebarLink},
	props: {},
	setup() {
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

		return { collapsed, toggleSideBar, sidebarWidth, beforeUnmount };
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

		<SidebarLink to="/" icon="home-icon">Home</SidebarLink>
		<SidebarLink to="/dashboard" icon="dashboard-icon">Dashboard</SidebarLink>
		<SidebarLink to="/about" icon="about-us-icon">About</SidebarLink>
		<SidebarLink to="/feedback" icon="feedback-icon">Feedback</SidebarLink>
		<SidebarLink to="/login" icon="login-icon">Login</SidebarLink>

		<span class="collapse-icon" :class="{'rotate-180': collapsed}" @click="toggleSideBar">
			<Svg name="sidebar-arrow.png" class="icon"/>
			<button class="iconButton"> <img id="icon" src="../../icons/sidebar-arrow.png"/> </button>
		</span>
	</div>
</template>



<style>
:root{
	--sidebar-bd-color: #242F40;
	--sidebar-item-hover: #CCA43B;
	--sidebar-item-active: #CCA43B;
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

	transition: 0.25s ease;

	display: flex;
	flex-direction: column;
}

.collapse-icon{
	position: absolute;
	bottom: 0;
	padding: 4px;
	transition: 0.2s linear;

}
#icon{
	height: 18px;
}
.iconButton{
	background-color: transparent;
	border-color: transparent;
}

.rotate-180{
	transform: rotate(180deg);
	transition: 0.2s linear;
}
</style>