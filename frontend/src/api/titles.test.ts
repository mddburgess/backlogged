import { api } from "./index";

describe("titles API", () => {
  describe("list()", () => {
    it("resolves on a 200 OK response", async () => {
      const response = api.titles.list();
      await expect(response).resolves.toStrictEqual([]);
    });
  });
});
