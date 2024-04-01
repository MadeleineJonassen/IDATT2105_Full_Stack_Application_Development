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

<template>
	<router-link :to="to" class="link" :class="{ active: isActive }">
		<Svg name="icon" :class="icon"/>
		<transition name="fade">
      <span v-if="!collapsed">
        <slot />
      </span>
		</transition>
	</router-link>
</template>

<style scoped>


.link {
	display: flex;
	align-items: center;

	cursor: pointer;
	position: relative;
	font-weight: 400;
	user-select: none;

	margin: 0.1em 0;
	padding: 0.4em;
	border-radius: 0.25em;
	height: 1.5em;

	color: white;
	text-decoration: none;
}

.link:hover {
	background-color: var(--sidebar-item-hover);
}

.link.active {
	background-color: var(--sidebar-item-active);
	color: #242F40;
}


</style>