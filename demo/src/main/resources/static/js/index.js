const App = {
  data() {
    return {
      entries: [
        {
          id: 1,
          title: "Familiar Colours, Hidden Intent",
          description:
            "Along the coastal cliffs, we met dragonborn marines, their banners imperial, mounted on winged beasts. They acknowledged our presence with nods, but their intent was inscrutable. Even allies carry weight in these borderlands.",
        },
        {
          id: 2,
          title: "The Weight of Purpose",
          description:
            "Each report we send feels meaningless, as though the truth we uncover will not satisfy the king or worse, that the realm expects something we cannot comprehend. Scouts ride in silence, each carrying their own unease.",
        },
      ],
    };
  },
  mounted() {},
  methods: {
    showCreateEntryModal() {
      this.entries.push({ id: uuidv4(), title: "", description: "" });
    },
    deleteEntry(id) {
      this.entries = this.entries.filter((entry) => entry.id != id);
    },
  },
};

Vue.createApp(App).mount("#app");
