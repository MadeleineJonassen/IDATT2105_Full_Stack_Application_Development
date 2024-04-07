<template>
	<router-link :to="to" class="link" :class="{ active: isActive }">
		<div class="icon-wrapper" v-if="collapsed">
			<Svg :name="icon" class="icon" />
		</div>
		<Transition name="fade">
      <span v-if="!collapsed" class="link-content">
        <Svg :name="icon" class="icon" />
        <slot />
      </span>
		</Transition>
	</router-link>
</template>

<script>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { collapsed } from './state.js'
import Svg from "@/assets/Svg.vue";

export default {
	components: {Svg},
	props: {
		to: { type: String, required: true },
		icon: { type: String, required: true }
	},
	setup(props) {
		const route = useRoute()
		const isActive = computed(() => route.path === props.to)
		return { isActive, collapsed }
	}
}
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
	transition: opacity 0.1ms ease;
}
.fade-enter,
.fade-leave-to {
	transform: translateX(20px);
	opacity: 0;
}
.link {
	font-weight: 400;
	padding: 0.2em;
	border-radius: 0.25em;
	height: 2.4em;
	color: white;
	text-decoration: none;
	margin: 1px;
}
.link:hover {
	background-color: var(--sidebar-item-hover);
}
.link.active {
	background-color: var(--sidebar-item-active);
	color: #242f40;
}
.icon {
	width: 2.2em;
	height: 2.2em;
	margin-right: 1vh;
}
.link-content {
	display: flex;
	align-items: center;
}
</style>
