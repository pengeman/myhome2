test-github-action:
	act \
		--env-file .env.github \
		-W .github/workflows/main.yml

