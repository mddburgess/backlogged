import { render, screen } from "@testing-library/react";

import App from "./App";

describe("App", () => {
  it("renders header", () => {
    render(<App />);
    const homeElement = screen.getByText(/Backlogged/i);
    expect(homeElement).toBeInTheDocument();
  });
});
