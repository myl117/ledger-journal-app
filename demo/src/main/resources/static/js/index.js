const App = {
  data() {
    return {
      entries: [],
    };
  },
  async mounted() {
    await this.getEntries();
  },
  methods: {
    showCreateEntryModal() {
      this.entries.push({ id: uuidv4(), title: "", description: "" });
    },
    deleteEntry(id) {
      this.entries = this.entries.filter((entry) => entry.id != id);
    },
    async getEntries() {
      const resp = await fetch("/api/entries");

      if (!resp.ok) {
        alert("An error occurred fetching entries");
        return;
      }

      this.entries = await resp.json();
    },
  },
};

Vue.createApp(App).mount("#app");
