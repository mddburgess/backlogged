import { api } from "./index";

describe("activities API", () => {
  describe("list()", () => {
    it("resolves on a 200 OK response", async () => {
      const response = api.activities.list();
      await expect(response).resolves.toStrictEqual([]);
    });
  });
});
