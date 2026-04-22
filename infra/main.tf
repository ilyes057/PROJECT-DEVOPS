resource "google_compute_instance" "ndarray_demo" {
  name         = var.instance_name
  machine_type = "e2-micro"
  zone         = var.zone

  tags = ["ndarray-demo"]

  boot_disk {
    initialize_params {
      image = "debian-cloud/debian-12"
      size  = 20
    }
  }

  network_interface {
    network = "default"

    access_config {
    }
  }

  metadata_startup_script = <<-EOT
    #!/bin/bash
    set -eux

    apt-get update
    apt-get install -y ca-certificates curl gnupg

    install -m 0755 -d /etc/apt/keyrings
    curl -fsSL https://download.docker.com/linux/debian/gpg -o /etc/apt/keyrings/docker.asc
    chmod a+r /etc/apt/keyrings/docker.asc

    echo \
      "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/debian \
      $(. /etc/os-release && echo "$VERSION_CODENAME") stable" \
      > /etc/apt/sources.list.d/docker.list

    apt-get update
    apt-get install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin

    docker pull ghcr.io/ilyes057/project-devops-demo:latest
    docker run --rm ghcr.io/ilyes057/project-devops-demo:latest > /var/log/ndarray-demo.log 2>&1
  EOT
}
