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
    createEntry() {
      this.entries.push({
        id: uuidv4Int(),
        title: "",
        description: "",
        isNewEntry: true,
      });
    },
    async createNewEntries(entries) {
      for (const entry of entries) {
        const resp = await fetch("/api/entries", {
          method: "POST",
          body: JSON.stringify(entry),
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!resp.ok) {
          alert("An error occurred creating a new entry");
        }
      }
    },
    async updateEntries(entries) {
      for (const entry of entries) {
        const resp = await fetch("/api/entries", {
          method: "PATCH",
          body: JSON.stringify(entry),
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (!resp.ok) {
          alert("An error occurred updating an entry");
        }
      }
    },
    async saveLedger() {
      const newEntries = this.entries.filter((entry) => entry.isNewEntry);
      const updateEntries = this.entries.filter((entry) => !entry.isNewEntry);

      await this.createNewEntries(newEntries);
      await this.updateEntries(updateEntries);

      this.getEntries();
    },
    async deleteEntry(id) {
      const resp = await fetch("/api/entries/" + id, {
        method: "DELETE",
      });

      if (!resp.ok) {
        alert("An error occurred deleting entry with id " + id);
        return;
      }

      this.entries = await resp.json();
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
