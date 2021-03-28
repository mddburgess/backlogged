import { api } from "./index";

describe("backlogs API", () => {
  describe("list()", () => {
    it("resolves on a 200 OK response", async () => {
      const response = api.backlogs.list();
      await expect(response).resolves.toStrictEqual([]);
    });
  });
});
